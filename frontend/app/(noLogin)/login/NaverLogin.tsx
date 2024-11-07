'use client'

export default function NaverLogin() {
  const url =
    'http://localhost:8080/oauth2/authorization/naver?redirect_uri=http://localhost:3000/'
  const onclick: any = async () => {
    try {
      await fetch(url, {
        method: 'GET',
        mode: 'no-cors',
      })
    } catch (e) {
      console.log(e)
    }
  }
  return (
    <div>
      {/* eslint-disable-next-line react/button-has-type */}
      <button onClick={onclick}>네이버로그인</button>
    </div>
  )
}
