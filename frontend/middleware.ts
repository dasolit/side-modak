import { NextResponse } from 'next/server'

export function middleware(req) {
  const { pathname } = req.nextUrl
  /*
  토큰 테스트 코드
  let accessToken = req.cookies.get('accessToken')
  let refreshToken = req.cookies.get('refreshToken')
  if (pathname === '/login') {
    console.log(accessToken)
    console.log(refreshToken)
  }
  */
  if (!req.cookies.get('accessToken') && pathname.startsWith('/auth')) {
    return NextResponse.redirect(new URL('/login', req.url))
  }

  return NextResponse.next()
}

export const config = {
  matcher: [
    '/((?!api|_next/static|_next/image|favicon.ico|sitemap.xml|robots.txt).*)',
  ],
}
