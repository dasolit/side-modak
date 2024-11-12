import React, { PropsWithChildren } from 'react'
import GlobalStyle from '@/styles/global-styles'
import styled from 'styled-components'
import { CookiesProvider } from 'react-cookie'

const ModakCookieProvider = ({ children }: PropsWithChildren) => {
  return <CookiesProvider>{children}</CookiesProvider>
}

export default ModakCookieProvider