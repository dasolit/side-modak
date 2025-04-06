'use client'

import { useState } from 'react'
import styled from 'styled-components'

export default function OnboardingPage() {
  const [inviteCode, setInviteCode] = useState('')

  const handleCreate = () => {
    alert('새 다이어리 만들기')
  }

  const handleJoin = () => {
    if (!inviteCode) {
      alert('초대 코드를 입력하세요')
      return
    }
    alert(`입장: ${inviteCode}`)
  }

  return (
    <Wrapper>
      <Content>
        <Title>다이어리 생성</Title>
        <SubTitle>
          다이어리를 생성하거나
          <br />
          초대 코드를 입력하세요
        </SubTitle>

        <CreateButton onClick={handleCreate}>새 다이어리 만들기</CreateButton>

        <CodeBox>
          <Input
            placeholder="초대 코드 입력"
            value={inviteCode}
            onChange={(e) => setInviteCode(e.target.value)}
          />
          <JoinButton onClick={handleJoin}>입장</JoinButton>
        </CodeBox>
      </Content>
    </Wrapper>
  )
}
const Wrapper = styled.div`
  background-color: #ffffff;
  min-height: 50vh;
  display: flex;
  justify-content: center;
  align-items: center;
`

const Content = styled.div`
  text-align: center;
  max-width: 360px;
  width: 100%;
`

const Title = styled.h1`
  font-size: 1.8rem;
  font-weight: 700;
  color: #3b2f2f;
  margin-bottom: 0.8rem;
`

const SubTitle = styled.p`
  font-size: 1rem;
  color: #7a6f6f;
  margin-bottom: 2rem;
  line-height: 1.6;
`

const CreateButton = styled.button`
  width: 100%;
  background-color: #5b3a2e;
  color: #fff;
  font-size: 1rem;
  padding: 1rem;
  border: none;
  border-radius: 16px;
  font-weight: 600;
  margin-bottom: 1.2rem;
  cursor: pointer;

  &:hover {
    background-color: #4a2f26;
  }
`

const CodeBox = styled.div`
  display: flex;
  border: 1.5px solid #e5d8c6;
  border-radius: 16px;
  overflow: hidden;
`

const Input = styled.input`
  flex: 1;
  padding: 0.8rem 1rem;
  border: none;
  font-size: 0.95rem;
  background-color: #fcf8f2;
  color: #5c524a;

  &::placeholder {
    color: #b8a89d;
  }

  &:focus {
    outline: none;
  }
`

const JoinButton = styled.button`
  background-color: transparent;
  color: #3b2f2f;
  font-weight: 700;
  padding: 0 1rem;
  border: none;
  cursor: pointer;

  &:hover {
    background-color: #f6eee7;
  }
`
