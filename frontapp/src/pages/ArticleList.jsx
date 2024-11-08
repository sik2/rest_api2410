import { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'

function ArticleList() {
    const [articleList, setArticleList] = useState([])

    useEffect(() => {
        fetch('http://localhost:8090/api/v1/articles')
            .then((res) => res.json())
            .then((res) => {
                console.log(res.data.articleList)
                setArticleList(res.data.articleList)
            })
    }, [])

    return (
        <>
            <h2>게시글 목록</h2>
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Subject</th>
                        <th>Content</th>
                        <th>Author</th>
                    </tr>
                </thead>
                <tbody>
                    {articleList.map((article) => (
                        <tr key={article.id}>
                            <td>{article.id}</td>
                            <td>
                                <Link to={`/article/detail/${article.id}`}>{article.subject}</Link>
                            </td>
                            <td>{article.content}</td>
                            <td>{article.author}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </>
    )
}

export default ArticleList
