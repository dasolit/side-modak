'use client'
import styled from 'styled-components'
import Image from 'next/image'
import Emoji from '@/public/images/main-emoji.png'

const CenterBlock = styled.div`
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 6rem;
  width: 100%;
  padding: 15px;
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
    <CenterBlock>
      <EmojiImage src={Emoji} alt="Emoji" />
      <ButtonGroup>
        <SocialButton bgColor="#FEE500" color="#000">
          카카오톡으로 계속하기
        </SocialButton>
        <SocialButton bgColor="#03C75A">네이버로 계속하기</SocialButton>
        {/*<SocialButton bgColor="#000000">Apple로 계속하기</SocialButton>*/}
      </ButtonGroup>
    </CenterBlock>
  )
}
