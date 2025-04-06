'use client'

import Link from 'next/link'
import { usePathname } from 'next/navigation'
import styled from 'styled-components'
import { FaCalendarAlt } from 'react-icons/fa'
import { BsChatHeartFill } from 'react-icons/bs'
import { GiAlarmClock } from 'react-icons/gi'
import { FaUserAstronaut } from 'react-icons/fa'

const navItems = [
  { name: '홈', path: '/', icon: FaCalendarAlt },
  { name: '채팅', path: '/chat', icon: BsChatHeartFill },
  { name: '디데이', path: '/dday', icon: GiAlarmClock },
  { name: '마이페이지', path: '/mypage', icon: FaUserAstronaut },
]

const NavContainer = styled.nav`
  position: fixed; // absolute → fixed로 변경: 스크롤 내려도 항상 보이게
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
      {navItems.map(({ name, path, icon: Icon }) => {
        const isActive = pathname === path
        return (
          <NavLink key={name} href={path}>
            <NavItem $active={isActive}>
              <div className="icon">
                <Icon />
              </div>
              {name}
            </NavItem>
          </NavLink>
        )
      })}
    </NavContainer>
  )
}
