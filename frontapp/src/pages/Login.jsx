function Login() {
    return (
        <>
            <h2>로그인</h2>

            <form action="/login" method="POST">
                <div>
                    <label for="username">아이디:</label>
                    <input type="text" id="username" name="username" required />
                </div>
                <div>
                    <label for="password">비밀번호:</label>
                    <input type="password" id="password" name="password" required />
                </div>
                <div>
                    <button type="submit">로그인</button>
                </div>
            </form>
        </>
    )
}

export default Login
