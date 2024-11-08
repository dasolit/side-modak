'use client'
import styled from 'styled-components'
import Image from 'next/image'

const SocialLoginArea = () => {
  return (
    <SocialLoginDiv>
      <Title>Sign in</Title>
      <LoginBox>
        <a
          href={`${process.env.NEXT_PUBLIC_BACKEND_URL}${process.env.NEXT_PUBLIC_NAVER_SOCIAL_LOGIN_URL}${process.env.NEXT_PUBLIC_FRONT_URL}`}
        >
          <Image
            className="naver"
            src="/images/btnG_official.png"
            alt="네이버 소셜 로그인 이미지"
            width={250}
            height={50}
          />
        </a>
        <a
          href={`${process.env.NEXT_PUBLIC_BACKEND_URL}${process.env.NEXT_PUBLIC_KAKAO_SOCIAL_LOGIN_URL}${process.env.NEXT_PUBLIC_FRONT_URL}`}
        >
          <Image
            className="kakao"
            src="/images/kakao_login_medium_narrow.png"
            alt="카카오 소셜 로그인 이미지"
            width={250}
            height={50}
          />
        </a>
        <a
          href={`${process.env.NEXT_PUBLIC_BACKEND_URL}${process.env.NEXT_PUBLIC_GOOGLE_SOCIAL_LOGIN_URL}${process.env.NEXT_PUBLIC_FRONT_URL}`}
        >
          <Image
            className="google"
            src="/images/web_neutral_sq_SI@2x.png"
            alt="구글 소셜 로그인 이미지"
            width={250}
            height={50}
          />
        </a>
      </LoginBox>
    </SocialLoginDiv>
  )
}

export default SocialLoginArea

const SocialLoginDiv = styled.div`
  background: #ffffff;
  align-content: center;
`

const Title = styled.span`
  font-size: var(--font-large-size3);
  color: var(--font-black-color);
`

const LoginBox = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  img {
    margin-top: 0.5rem;
  }
`
