'use client'

import { useEffect, useState } from 'react'
import Calendar from 'react-calendar'
import styled from 'styled-components'
import 'react-calendar/dist/Calendar.css'

const CalendarWrapper = styled.div`
  padding: 16px;
  .react-calendar {
    width: 100%;
    border: none;
  }
`

export default function CalendarView({
  onChange,
}: {
  onChange: (date: Date) => void
}) {
  const [mounted, setMounted] = useState(false)
  const [value, setValue] = useState(new Date())

  useEffect(() => {
    setMounted(true)
  }, [])

  if (!mounted) return null

  const handleChange = (val: Date) => {
    setValue(val)
    onChange(val)
  }

  return (
    <CalendarWrapper>
      <Calendar
        locale="ko-KR"
        onChange={handleChange}
        value={value}
        calendarType="gregory"
      />
    </CalendarWrapper>
  )
}
