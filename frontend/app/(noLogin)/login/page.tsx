'use client'

import styled from 'styled-components'
import Image from 'next/image'
import ModaLogo from '@/public/images/moda-logo.png'
import Emoji from '@/public/images/main-emoji.png'

const PageWrapper = styled.div`
  width: 430px;
  height: 100dvh;
  background-color: #fff;
  margin: 0 auto;
  padding: 2rem 1.5rem;
  display: flex;
  flex-direction: column;
  align-items: center;
`

const LogoWrapper = styled.div`
  width: 100%;
  display: flex;
  justify-content: center;
  margin-bottom: 2rem;
`

const LogoImage = styled(Image)`
  width: 120px;
  height: auto;
`

const CenterBlock = styled.div`
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 2rem;
  width: 100%;
`

const EmojiImage = styled(Image)`
  width: 200px;
  height: auto;
`

const SocialButton = styled.button<{ bgColor: string; color?: string }>`
  background-color: ${(props) => props.bgColor};
  color: ${(props) => props.color || '#fff'};
  width: 100%;
  padding: 1rem;
  border: none;
  border-radius: 8px;
  font-size: 1rem;
  font-weight: 600;
  cursor: pointer;
`

const ButtonGroup = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  gap: 0.85rem;
`

export default function LoginPage() {
  return (
    <PageWrapper>
      <LogoWrapper>
        <LogoImage src={ModaLogo} alt="Moda Logo" width={120} height={30} />
      </LogoWrapper>

      <CenterBlock>
        <EmojiImage src={Emoji} alt="Emoji" />
        <ButtonGroup>
          <SocialButton bgColor="#FEE500" color="#000">
            카카오톡으로 계속하기
          </SocialButton>
          <SocialButton bgColor="#03C75A">네이버로 계속하기</SocialButton>
          <SocialButton bgColor="#000000">Apple로 계속하기</SocialButton>
        </ButtonGroup>
      </CenterBlock>
    </PageWrapper>
  )
}
