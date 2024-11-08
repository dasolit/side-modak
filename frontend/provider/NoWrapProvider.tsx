'use client'
import GlobalStyle from '@/styles/global-styles'
import React, { PropsWithChildren } from 'react'
import styled from 'styled-components'

const NoWrapProvider = ({ children }: PropsWithChildren) => {
  return (
    <Wrapper>
      <GlobalStyle />
      <InnerWrapper>{children}</InnerWrapper>
    </Wrapper>
  )
}

export default NoWrapProvider

const Wrapper = styled.div`
  display: flex;
  justify-content: center;
  height: 100%;
`

const InnerWrapper = styled.div`
  border-radius: 0.3rem;
  align-content: center;
`
