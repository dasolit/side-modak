import { Cookies } from 'react-cookie'

const cookies: Cookies = new Cookies()

export const setCookie = (name: string, value: string, options?: any) =>
  cookies.set(name, value, { ...options })

export const getCookie = (name: string) => cookies.get(name)
