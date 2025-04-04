'use client'
import GlobalStyle from '@/styles/global-styles'
import React, { PropsWithChildren } from 'react'
import styled from 'styled-components'

const HomeWrapperProvider = ({ children }: PropsWithChildren) => {
  return (
    <Wrapper>
      <GlobalStyle />
      {children}
    </Wrapper>
  )
}

export default HomeWrapperProvider

const Wrapper = styled.div``
