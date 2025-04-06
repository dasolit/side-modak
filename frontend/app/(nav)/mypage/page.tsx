'use client'

import styled from 'styled-components'

export default function MyPage() {
  const isOwner = true // 소유자 여부에 따라 조건 분기 가능

  return (
    <Wrapper>
      <SectionTitle>다이어리 설정</SectionTitle>
      <MenuList>
        {isOwner && (
          <MenuButton onClick={() => alert('다이어리 명칭 변경')}>
            다이어리 명칭 변경
          </MenuButton>
        )}
        <MenuButton onClick={() => alert('멤버 목록 보기')}>
          다이어리 멤버 조회
        </MenuButton>
        {isOwner && (
          <MenuButton onClick={() => alert('멤버 강퇴하기')}>
            다이어리 멤버 강퇴
          </MenuButton>
        )}
      </MenuList>

      <Divider />

      <SectionTitle>기타</SectionTitle>
      <MenuList>
        <MenuButton onClick={() => alert('다이어리 나가기')} $danger>
          다이어리 나가기
        </MenuButton>
        <MenuButton onClick={() => alert('로그아웃')} $danger>
          로그아웃
        </MenuButton>
      </MenuList>
    </Wrapper>
  )
}

const Wrapper = styled.div`
  padding: 1.5rem;
`

const SectionTitle = styled.h2`
  font-size: 1rem;
  font-weight: bold;
  color: #333;
`

const MenuList = styled.div`
  display: flex;
  flex-direction: column;
  gap: 0.75rem;
  margin-bottom: 2rem;
`

const MenuButton = styled.button<{ $danger?: boolean }>`
  background-color: #fff;
  padding: 1rem;
  border-radius: 8px;
  border: 1px solid ${({ $danger }) => ($danger ? '#fca5a5' : '#ddd')};
  color: ${({ $danger }) => ($danger ? '#dc2626' : '#111')};
  font-size: 0.95rem;
  text-align: left;
  cursor: pointer;
  transition: background 0.2s;

  &:hover {
    background-color: ${({ $danger }) => ($danger ? '#fef2f2' : '#f3f4f6')};
  }
`

const Divider = styled.div`
  height: 1px;
  background-color: #e5e7eb;
  margin: 2rem 0 1rem;
`
