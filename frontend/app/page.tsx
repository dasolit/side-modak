import Link from 'next/link'
import Logout from '@/component/login/Logout'
import { cookies } from 'next/headers'

export default function Home() {
  const cookie = cookies()
  const accessToken = cookie.get('accessToken')
  console.log(accessToken)
  return (
    <main>
      {!accessToken ? (
        <Link href="/login">Login</Link>
      ) : (
        <Logout accessToken={accessToken} />
      )}
    </main>
  )
}
