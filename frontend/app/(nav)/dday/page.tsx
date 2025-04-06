'use client'

import styled from 'styled-components'

const today = new Date()

const ddayList = [
  {
    id: 1,
    name: '최다솔',
    description: '연구팀 - 프로젝트 기획',
    time: '오전 9:00 - 오전 9:30',
    role: '기술연구소 인턴',
    profile: '/avatar1.jpg',
    date: '2025-04-08',
  },
  {
    id: 2,
    name: '최다솔',
    description: '수습 연구원',
    time: '',
    role: '기술연구소 인턴',
    profile: '/avatar2.jpg',
    date: '2025-03-30',
  },
]

export default function DdayPage() {
  return (
    <Wrapper>
      {ddayList.map((item) => {
        const isPast = new Date(item.date) < today

        return (
          <Card key={item.id} $isPast={isPast}>
            <ProfileBox>
              <Avatar src={item.profile} alt={item.name} />
              <UserInfo>
                <Name>{item.name}</Name>
                <Role>{item.role}</Role>
              </UserInfo>
              <DateText>{item.date.replace(/-/g, '.')}</DateText>
            </ProfileBox>

            <Content>
              <Mention>
                아래 일정에 <b>{item.name}</b>님이 회원님을 언급하셨습니다.
              </Mention>
              <InfoBox>
                <Tag>{item.description}</Tag>
                {item.time && <Time>{item.time}</Time>}
              </InfoBox>
            </Content>
          </Card>
        )
      })}
    </Wrapper>
  )
}

const Wrapper = styled.div`
  padding: 1rem;
`

const Card = styled.div<{ $isPast: boolean }>`
  background: #fff;
  border-radius: 12px;
  padding: 1rem;
  margin-bottom: 1rem;
  box-shadow: 0 0 6px rgba(0, 0, 0, 0.05);
  filter: ${({ $isPast }) => ($isPast ? 'blur(2px)' : 'none')};
  opacity: ${({ $isPast }) => ($isPast ? 0.5 : 1)};
  transition: 0.3s;
`

const ProfileBox = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
`

const Avatar = styled.img`
  width: 42px;
  height: 42px;
  border-radius: 50%;
  object-fit: cover;
`

const UserInfo = styled.div`
  flex: 1;
  margin-left: 0.75rem;
`

const Name = styled.div`
  font-weight: 600;
`

const Role = styled.div`
  font-size: 0.75rem;
  color: #888;
`

const DateText = styled.div`
  font-size: 0.75rem;
  color: #999;
  white-space: nowrap;
`

const Content = styled.div`
  margin-top: 0.75rem;
`

const Mention = styled.div`
  font-size: 0.9rem;
  margin-bottom: 0.5rem;
`

const InfoBox = styled.div`
  background-color: #eef2ff;
  padding: 0.6rem 0.8rem;
  border-radius: 8px;
`

const Tag = styled.div`
  font-weight: 500;
  margin-bottom: 0.25rem;
`

const Time = styled.div`
  font-size: 0.75rem;
  color: #555;
`
