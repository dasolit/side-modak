'use client'

import LoginHeader from '@/component/login/LoginHeader'
import LoginBody from '@/component/login/LoginBody'
import styled from 'styled-components'

const LoginIndex = () => {
  return (
    <LoginIndexDiv>
      <LoginHeader />
      <LoginBody />
    </LoginIndexDiv>
  )
}

export default LoginIndex
const LoginIndexDiv = styled.div`
  width: 40rem;
`
