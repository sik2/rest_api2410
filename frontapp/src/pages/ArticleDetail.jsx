import { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'

function ArticleDetail() {
    const [article, setArticle] = useState({})
    const param = useParams()

    useEffect(() => {
        fetch(`http://localhost:8090/api/v1/articles/${param.id}`)
            .then((res) => res.json())
            .then((res) => {
                setArticle(res.data.article)
            })
    }, [])

    return (
        <>
            <div>
                <h2>{article.subject}</h2>
                <p>{article.content}</p>
                <p>{article.author}</p>
            </div>
        </>
    )
}

export default ArticleDetail
