'use client'

const Logout = ({ accessToken }) => {
  const onclick = () => {
    console.log('로그아웃')
    console.log(accessToken)
    const logout = async () => {
      try {
        const res = await fetch(
          `${process.env.NEXT_PUBLIC_BACKEND_URL}/user/logout`,
          {
            headers: {
              ['Authorization']: `Bearer ${accessToken.value}`,
              'Access-Control-Allow-Origin': 'http://localhost:8080',
              'X-AUTH-TOKEN': `${localStorage.getItem('X-AUTH-TOKEN')}`,
            },
          },
        )
      } catch (e) {
        console.error(e)
      }
    }
    logout()
  }
  return <a onClick={onclick}>로그아aaa웃</a>
}
export default Logout
