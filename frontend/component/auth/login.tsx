'use client'

export default function Login() {
  return (
    <>
      <span>Login</span>
      <br />
      <a href="http://localhost:8080/oauth2/authorization/naver?redirect_uri=http://localhost:3000/">
        네이버로그인
      </a>
      <br />
      <a href="http://localhost:8080/oauth2/authorization/kakao?redirect_uri=http://localhost:3000/">
        카카오로그인
      </a>
      <br />
      <a href="http://localhost:8080/oauth2/authorization/google?redirect_uri=http://localhost:3000/">
        구글 로그인
      </a>
    </>
  )
}
