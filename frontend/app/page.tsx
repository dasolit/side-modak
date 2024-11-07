import Link from 'next/link'

export default function Home() {
  return (
    <main>
      <Link href="/login">Login</Link>
      <h1> 메인 페이지</h1>
    </main>
  )
}
