'use client'

import { useState } from 'react'
import CalendarView from '@/component/schedule/CalendarView'
import ScheduleList from '@/component/schedule/ScheduleList'

export default function HomePage() {
  const [selectedDate, setSelectedDate] = useState(new Date())

  const dummy = [
    { id: 1, title: '팀 회의', date: '2025-04-17' },
    { id: 2, title: '디자인 회의', date: '2025-04-18' },
    { id: 3, title: '5월 워크숍', date: '2025-05-01' },
  ]

  // 이번 달 시작일과 종료일 계산
  const year = selectedDate.getFullYear()
  const month = selectedDate.getMonth()
  const startOfMonth = new Date(year, month, 1).toISOString().split('T')[0]
  const endOfMonth = new Date(year, month + 1, 0).toISOString().split('T')[0]

  const filtered = dummy.filter(
    (item) => item.date >= startOfMonth && item.date <= endOfMonth,
  )

  return (
    <>
      <CalendarView onChange={setSelectedDate} />
      <ScheduleList items={filtered} />
    </>
  )
}
