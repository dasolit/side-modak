'use client'

import { useEffect, useRef, useState } from 'react'
import styled from 'styled-components'

const messages = [
  {
    id: 1,
    user: 'me',
    nickname: '나',
    text: '오늘 회의 하나요?',
    time: '08:00',
  },
  {
    id: 2,
    user: 'you',
    nickname: '홍길동',
    text: '네, 몇 시가 괜찮으세요?',
    time: '08:04',
  },
  {
    id: 3,
    user: 'me',
    nickname: '나',
    text: '점심 이후가 좋을 것 같아요. 오전에 다른 일정이 있어요.',
    time: '08:15',
  },
  {
    id: 4,
    user: 'you',
    nickname: '홍길동',
    text: '좋아요! 회의 잘 하세요 :)',
    time: '08:22',
  },
  {
    id: 5,
    user: 'me',
    nickname: '나',
    text: '감사합니다. 14시에 뵐게요.',
    time: '08:30',
  },
]

export default function ChatPage() {
  const [input, setInput] = useState('')
  const bottomRef = useRef<HTMLDivElement | null>(null)

  useEffect(() => {
    bottomRef.current?.scrollIntoView({ behavior: 'smooth' })
  }, [])

  return (
    <Wrapper>
      <ChatBox>
        {messages.map((msg) => (
          <MsgRow key={msg.id} $isMe={msg.user === 'me'}>
            <Info>
              <Nickname>{msg.nickname}</Nickname>
            </Info>
            <Bubble $isMe={msg.user === 'me'}>{msg.text}</Bubble>
            <Time>{msg.time}</Time>
          </MsgRow>
        ))}
        <div ref={bottomRef} />
      </ChatBox>

      <InputBar>
        <Input
          placeholder="메시지를 입력하세요..."
          value={input}
          onChange={(e) => setInput(e.target.value)}
        />
        <SendBtn>전송</SendBtn>
      </InputBar>
    </Wrapper>
  )
}

const Wrapper = styled.div`
  height: 80vh;
  display: flex;
  flex-direction: column;
  position: relative;
`

const ChatBox = styled.div`
  flex: 1;
  overflow-y: auto;
  padding: 1rem;
`

const MsgRow = styled.div<{ $isMe: boolean }>`
  display: flex;
  flex-direction: column;
  align-items: ${({ $isMe }) => ($isMe ? 'flex-end' : 'flex-start')};
  margin-bottom: 1rem;
`

const Info = styled.div`
  font-size: 0.75rem;
  color: #888;
  margin-bottom: 0.3rem;
`

const Nickname = styled.span`
  font-weight: 500;
`

const Time = styled.span`
  font-style: italic;
  font-size: 0.7rem;
  color: #999;
  margin-top: 2px;
`

const Bubble = styled.div<{ $isMe: boolean }>`
  background-color: ${({ $isMe }) => ($isMe ? '#dff0ff' : '#fff')};
  border: 1px solid #ddd;
  padding: 0.7rem 1rem;
  border-radius: 12px;
  max-width: 66%;
  font-size: 0.95rem;
  line-height: 1.4;
  word-break: break-word;
  white-space: pre-wrap;
`

const InputBar = styled.div`
  position: fixed;
  bottom: 56px;
  width: 430px;
  display: flex;
  align-items: center;
  padding: 0.75rem 1rem;
  border-top: 1px solid #ddd;
  z-index: 1000;
  box-shadow: 0 -2px 5px rgba(0, 0, 0, 0.03);
  background-color: #fff;
`

const Input = styled.input`
  flex: 1;
  border: none;
  border-radius: 20px;
  padding: 0.7rem 1rem;
  font-size: 1rem;
  background-color: #f1f1f1;
  margin-right: 0.5rem;

  &:focus {
    outline: none;
  }
`

const SendBtn = styled.button`
  border: none;
  background: none;
  color: #f76c6c;
  font-weight: bold;
  font-size: 1rem;
  min-width: 48px;
  cursor: pointer;
`
