'use client'

import { useState } from 'react'

export default function NaverLogin() {
  const onclick: any = async () => {
    try {
      const res = await fetch(
        'http://localhost:8080/oauth2/authorization/naver?redirect_uri=http://localhost:3000/',
        { mode: 'no-cors' },
      ).then((res) => console.log(res))
    } catch (e) {
      alert(e)
    }
  }
  return (
    <div>
      {/* eslint-disable-next-line react/button-has-type */}
      <button onClick={onclick}>네이버로그인</button>
    </div>
  )
}
