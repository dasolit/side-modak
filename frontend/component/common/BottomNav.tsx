'use client'

import Link from 'next/link'
import { usePathname } from 'next/navigation'
import styled from 'styled-components'

const navItems = [
  { name: '홈', path: '/', icon: '📅' },
  { name: '채팅', path: '/chat', icon: '💬' },
  { name: '디데이', path: '/dday', icon: '⏰' },
  { name: '마이페이지', path: '/mypage', icon: '👤' },
]

const NavContainer = styled.nav`
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  margin: 0 auto;
  width: 430px;
  height: 56px;
  background-color: white;
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  border-top: 1px solid #e5e5e5;
  z-index: 100;
`

const NavLink = styled(Link)`
  text-decoration: none;
  color: inherit;
`

// ✅ $active: transient prop으로 DOM에 안 들어감
const NavItem = styled.div<{ $active: boolean }>`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100%;
  font-size: 12px;
  font-weight: ${({ $active }) => ($active ? '600' : '400')};
  background-color: ${({ $active }) => ($active ? '#e0edff' : 'transparent')};
  color: ${({ $active }) => ($active ? '#2563eb' : '#9ca3af')};
  transition: all 0.2s ease;

  &:hover {
    background-color: ${({ $active }) => ($active ? '#d6e9ff' : '#f6f6f6')};
  }

  .icon {
    font-size: 18px;
    margin-bottom: 2px;
  }
`

export default function BottomNav() {
  const pathname = usePathname()

  return (
    <NavContainer>
      {navItems.map((item) => {
        const isActive = pathname === item.path
        return (
          <NavLink key={item.name} href={item.path}>
            <NavItem $active={isActive}>
              <div className="icon">{item.icon}</div>
              {item.name}
            </NavItem>
          </NavLink>
        )
      })}
    </NavContainer>
  )
}
