'use client'
import ModaLogo from '@/public/images/moda-logo.png'
import styled from 'styled-components'
import Image from 'next/image'

const LogoWrapper = styled.div`
  width: 100%;
  display: flex;
  justify-content: center;
  margin-bottom: 2rem;
  padding: 10px;
`

const LogoImage = styled(Image)`
  width: 120px;
  height: auto;
`

export default function Header() {
  return (
    <LogoWrapper>
      <LogoImage src={ModaLogo} alt="Moda Logo" width={120} height={30} />
    </LogoWrapper>
  )
}
