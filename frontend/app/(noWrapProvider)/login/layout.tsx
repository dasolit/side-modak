import NoWrapProvider from "@/provider/NoWrapProvider";

export default function LoginLayout({ children }: { children: JSX.Element }) {
  return <NoWrapProvider>{children}</NoWrapProvider>
}
