import styled from 'styled-components'

const ListContainer = styled.div`
  padding: 16px;
`

const Item = styled.div`
  background: #f3f3f3;
  border-radius: 10px;
  padding: 12px;
  margin-bottom: 10px;
  font-weight: 500;
`

export default function ScheduleList({
  items,
}: {
  items: { id: number; title: string }[]
}) {
  return (
    <ListContainer>
      {items.length === 0 ? (
        <div>선택한 날짜에 일정이 없습니다.</div>
      ) : (
        items.map((item) => (
          <Item key={item.id}>
            [{item.date}] {item.title}
          </Item>
        ))
      )}
    </ListContainer>
  )
}
