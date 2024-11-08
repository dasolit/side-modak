import styled from 'styled-components'
import GreetingArea from '@/component/login/GreetingArea'
import SocialLoginArea from '@/component/login/SocialLogin'

const LoginBody = () => {
  return (
    <LoginBodyDiv>
      <SocialLoginArea />
      <GreetingArea />
    </LoginBodyDiv>
  )
}

export default LoginBody

const LoginBodyDiv = styled.div`
  display: grid;
  grid-template-columns: 50% 50%;
  text-align: center;
  background: white;
  width: 100%;
  height: 30rem;
  box-shadow:
    0 1rem 1.5rem rgba(0, 0, 0, 0.25),
    0 1rem 0.5rem rgba(0, 0, 0, 0.22);
  overflow: hidden;
  border-radius: 0.5rem;
`
