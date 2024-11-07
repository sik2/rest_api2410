import { useState } from 'react'

function ArticleList() {
    const [articleList, setArticleList] = useState([])

    fetch('http://localhost:8090/api/v1/articles')
        .then((res) => res.json())
        .then((res) => {
            console.log(res)
        })

    return (
        <>
            <ul>
                {articleList.map((article) => (
                    <li key={aritlce.id}>{article.subject}</li>
                ))}
            </ul>
        </>
    )
}

export default ArticleList
