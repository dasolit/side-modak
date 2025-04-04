import type { Metadata } from 'next'
import './globals.css'
import StyledComponentsRegistry from '@/lib/registry'
import BottomNav from '@/component/common/BottomNav'
import Header from '@/component/common/Header'

export const metadata: Metadata = {
  title: 'Moda',
  description: '환영합니다!',
}

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode
}>) {
  return (
    <html lang="en">
      <body>
        <StyledComponentsRegistry>
          <div className="container">
            <Header />
            {children}
          </div>
        </StyledComponentsRegistry>
      </body>
    </html>
  )
}
