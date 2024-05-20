package com.mju.BackEnd.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mju.BackEnd.Dto.*;

import com.mju.BackEnd.Service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class questionController {

    @RequestMapping("/")
    public String root() {
        List<GenerateTemplate> templates = List.of(
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ),
                new GenerateTemplate(
                        new KinDescription("maroon5노래 중 payphone에 대해 질문입니다...",
                                "#찾고있음 #여자가수 #노래제목 #Maroon5 #Jayesslee",
                                "https://kin.naver.com/qna/detail.naver?d1id=3&dirId=30213&docId=158992279&qb=cGF5cGhvbmUgbWFyb29uNQ==&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0"),
                        "https://oaidalleapiprodscus.blob.core.windows.net/private/org-q0GUNTYDiZnBWkDtzrPk2TUu/user-ycTLh3JwKGfwvrHJIaNrcNCi/img-6HuYn8O7r7PZ6f99gfY36UTE.png?st=2024-05-20T08%3A19%3A59Z&se=2024-05-20T10%3A19%3A59Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-05-19T21%3A39%3A31Z&ske=2024-05-20T21%3A39%3A31Z&sks=b&skv=2021-08-06&sig=udrLjHWbkL3iHsuLGMHudZj2qBH6JYExUIFiDx/q0Ic%3D"
                ));
        ObjectMapper objectMapper = new ObjectMapper();
        String ret="";
        try {
            ret = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(templates);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
