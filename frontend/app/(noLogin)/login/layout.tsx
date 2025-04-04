'use client'

import NoWrapProvider from '@/provider/NoWrapProvider'

export default function Layout({ children }: { children: React.ReactNode }) {
  return <NoWrapProvider>{children}</NoWrapProvider>
}
