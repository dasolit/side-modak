import styled from 'styled-components'

const LoginHeader = () => {
  return (
    <LoginHeaderDiv>
      <WelcomeMsgP>Modak에 오신걸 진심으로 환영합니다!</WelcomeMsgP>
    </LoginHeaderDiv>
  )
}
export default LoginHeader

const LoginHeaderDiv = styled.div`
  padding: 1rem;
`
const WelcomeMsgP = styled.p`
  color: black;
  font-weight: normal;
  font-size: var(--font-large-size);
  text-align: center;
`
