import React, { PropsWithChildren } from 'react'
import { CookiesProvider } from 'react-cookie'

const ModakCookieProvider = ({ children }: PropsWithChildren) => {
  return <CookiesProvider>{children}</CookiesProvider>
}

export default ModakCookieProvider
