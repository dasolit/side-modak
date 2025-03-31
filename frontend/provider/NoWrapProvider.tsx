'use client'
import GlobalStyle from '@/styles/global-styles'
import React, { PropsWithChildren } from 'react'
import styled from 'styled-components'

const NoWrapProvider = ({ children }: PropsWithChildren) => {
  return (
    <Wrapper>
      <GlobalStyle />
      <InnerWrapper className="container">{children}</InnerWrapper>
    </Wrapper>
  )
}

export default NoWrapProvider

const Wrapper = styled.div``

const InnerWrapper = styled.div``
