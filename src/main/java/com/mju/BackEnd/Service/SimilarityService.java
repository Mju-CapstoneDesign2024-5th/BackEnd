package com.mju.BackEnd.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mju.BackEnd.Dto.GenerateTemplate;
import com.mju.BackEnd.Entity.Contents;
import com.mju.BackEnd.Service.DBService;

import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SimilarityService {

    private final DBService dbService;

    @Autowired
    public SimilarityService(DBService dbService) {
        this.dbService = dbService;
    }


    public List<String> Similarity(String target_id) throws JsonProcessingException {
        List<GenerateTemplate> contents = dbService.printAllContents(1000);
        //System.out.println(contents);
        List<Article> articles = contents.stream()
                .map(template -> new Article(template.getID(), tokenizeDescription(template.getDescription())))
                .collect(Collectors.toList());

        // 모든 고유 태그 목록 생성
        Set<String> uniqueTags = articles.stream()
                .flatMap(article -> article.getTags().stream())
                .collect(Collectors.toSet());

        List<String> uniqueTagList = new ArrayList<>(uniqueTags);

        // 태그 벡터 생성
        double[][] tagVectors = articles.stream()
                .map(article -> createTagVector(article.getTags(), uniqueTagList))
                .toArray(double[][]::new);

        // 코사인 유사도 계산
        RealMatrix matrix = new BlockRealMatrix(tagVectors);
        RealMatrix cosineSimMatrix = calculateCosineSimilarity(matrix);

        // 예시: 글 ID가 1인 글에 대해 가장 유사한 5개의 글 찾기
        List<Article> top5SimilarArticles = getTop5SimilarArticles(target_id, articles, cosineSimMatrix);
        List<String> idList = new ArrayList<>();
        for (Article article : top5SimilarArticles) {
            idList.add(article.getId());
        }

        return idList;
    }
    public static List<String> tokenizeDescription(String description) {
        return Arrays.stream(description.split(" "))
                .map(tag -> tag.replace("#", ""))
                .collect(Collectors.toList());
    }

    private static double[] createTagVector(List<String> tags, List<String> uniqueTags) {
        double[] vector = new double[uniqueTags.size()];
        for (int i = 0; i < uniqueTags.size(); i++) {
            vector[i] = tags.contains(uniqueTags.get(i)) ? 1 : 0;
        }
        return vector;
    }

    private static RealMatrix calculateCosineSimilarity(RealMatrix matrix) {
        int rows = matrix.getRowDimension();
        RealMatrix similarityMatrix = new BlockRealMatrix(rows, rows);
        for (int i = 0; i < rows; i++) {
            for (int j = i; j < rows; j++) {
                double similarity = cosineSimilarity(matrix.getRow(i), matrix.getRow(j));
                similarityMatrix.setEntry(i, j, similarity);
                similarityMatrix.setEntry(j, i, similarity);
            }
        }
        return similarityMatrix;
    }

    private static double cosineSimilarity(double[] vec1, double[] vec2) {
        double dotProduct = 0.0;
        double normVec1 = 0.0;
        double normVec2 = 0.0;
        for (int i = 0; i < vec1.length; i++) {
            dotProduct += vec1[i] * vec2[i];
            normVec1 += vec1[i] * vec1[i];
            normVec2 += vec2[i] * vec2[i];
        }
        return dotProduct / (Math.sqrt(normVec1) * Math.sqrt(normVec2));
    }

    private static List<Article> getTop5SimilarArticles(String articleId, List<Article> articles, RealMatrix cosineSimMatrix) {
        int articleIndex = -1;
        //System.out.println("Article ID: " + articleId);
        for (int i = 0; i < articles.size(); i++) {
            if (Objects.equals(articles.get(i).getId(), articleId)) {
                articleIndex = i;
                break;
            }
        }

        if (articleIndex == -1) {
            throw new IllegalArgumentException("Article ID not found");
        }

        double[] similarityScores = cosineSimMatrix.getRow(articleIndex);
        List<Map.Entry<Integer, Double>> similarityList = new ArrayList<>();
        for (int i = 0; i < similarityScores.length; i++) {
            if (i != articleIndex) {
                similarityList.add(new AbstractMap.SimpleEntry<>(i, similarityScores[i]));
            }
        }

        similarityList.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));
        List<Article> top5SimilarArticles = similarityList.stream()
                .limit(5)
                .map(entry -> articles.get(entry.getKey()))
                .collect(Collectors.toList());

        return top5SimilarArticles;
    }
}

class Article {
    private String id;
    private List<String> tags;

    public Article(String id, List<String> tags) {
        this.id = id;
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public List<String> getTags() {
        return tags;
    }
}
