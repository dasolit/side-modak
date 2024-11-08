import styled from 'styled-components'

const GreetingArea = () => {
  return (
    <GreetingDiv>
      <Title>안녕하세요</Title>
      <Description>
        Modak은 음성 & 화상 & 텍스트 채팅 애플리케이션 입니다. 모두가 다같이
        즐거운 시간을 보냈으면 좋겠습니다.
      </Description>
    </GreetingDiv>
  )
}

export default GreetingArea

const GreetingDiv = styled.div`
  background: #ff445f;
  align-content: center;
  span {
    white-space: pre-line;
    padding: 0.5rem;
  }
`

const Title = styled.span`
  display: block;
  font-size: var(--font-large-size2);
  font-weight: bold;
`

const Description = styled.span`
  display: block;
`
