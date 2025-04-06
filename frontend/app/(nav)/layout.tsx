'use client'

import HomeWrapperProvider from '@/provider/HomeWrapperProvider'
import BottomNav from '@/component/common/BottomNav'

export default function Layout({ children }: { children: React.ReactNode }) {
  return (
    <HomeWrapperProvider>
      {children}
      <BottomNav />
    </HomeWrapperProvider>
  )
}
