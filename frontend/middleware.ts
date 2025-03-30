import { NextResponse } from 'next/server'
import type { NextRequest } from 'next/server'

export function middleware(request: NextRequest) {
  const { pathname } = request.nextUrl
  const accessToken = request.cookies.get('accessToken')?.value

  // accessToken이 없으면 login으로
  /*
  if (pathname === '/login') {
    return NextResponse.next()
  }

  if (!accessToken) {
    const loginUrl = request.nextUrl.clone()
    loginUrl.pathname = '/login'
    return NextResponse.redirect(loginUrl)
  }
  */

  return NextResponse.next()
}
