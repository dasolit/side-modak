import { createGlobalStyle } from 'styled-components'

const GlobalStyle = createGlobalStyle`
  :root {
    --font-color: #ffffff;
    --font-black-color: #000000;
    --font-gray-color: #B3B3B5;
    --bg-color: #F6F5F7;
      
    --bg-even-color: #181820;

    --white-color: #ffffff;

    --blue-color: #4663e3;
    --red-color: #e34646;

    --black-color: #111219;
    --black-color-200: #171820;
    --black-color-300: #1d1d26;

    --gray-color-100: #22262C;
    --gray-color-200: #31313D;
    --gray-color-300: #484A4D;
    --gray-color-400: #8D8F92;
    --gray-color-500: #ACACAE;
    --gray-color-700: #C9C8C4;

    --primary-color: #98FB98;
    --hover-primary-color: #98FB98E6;

    --skeleton-base-color: #444;
    --skeleton-hightlight-color: #8D8F92DD;

    --font-zero-size: 0.063rem; // 1px
    --font-micro-size: 0.5625rem; // 8.8px
    --font-tiny-size: 0.75rem; // 12px
    --font-small-size: 0.875rem; // 14px 
    --font-normal-size: 1rem; // 16px
    --font-medium-size: 1.125rem; // 18px
    --font-large-size: 1.25rem; // 20px
    --font-large-size2: 1.5rem; // 24px
    --font-large-size3: 1.875rem; // 30px
  }

  * {
    padding: 0;
    margin: 0;
    font-size: inherit;
    color: inherit;
    box-sizing: border-box;
    -webkit-tap-highlight-color: rgba(255, 255, 255, 0); // 버튼 클릭시 음영 없애기
    -webkit-touch-callout: none; // link long touch 방지
    /* drag 방지 */
    user-select: none;
    -ms-user-select: none;
    -moz-user-select: none;
    -webkit-user-select: none;
  }

  *:focus-visible {
    outline: none;
  }

  *:focus {
    box-shadow: none !important;
  }

  a, button {
    display: inline-block;
    border: none;
    text-decoration: none;
    background-color: transparent;
    font: inherit;
    color: inherit;
    text-align: left;
  }

  input, textarea {
    -moz-user-select: auto;
    -webkit-user-select: auto;
    -ms-user-select: auto;
    user-select: auto;
  }

  input:focus, textarea:focus {
    outline: none;
  }

  input[type=email],
  input[type=text],
  input[type=password] {
    line-height: 1.25rem;
    -webkit-appearance: none; /* 그림자 */
  }

  /* IE의 경우 */
  input::-ms-clear,
  input::-ms-reveal {
    display: none;
  }

  /* 크롬의 경우 */
  input::-webkit-search-decoration,
  input::-webkit-search-cancel-button,
  input::-webkit-search-results-button,
  input::-webkit-search-results-decoration {
    display: none;
  }

  input::-webkit-credentials-auto-fill-button {
    opacity: 0 !important;
  }

  input:focus-visible {
    outline: none;
  }

  /* autocomplete - background color hide */
  input:-webkit-autofill, textarea:-webkit-autofill, select:-webkit-autofill {
    transition-delay: 3600s;
  }

  /* RESET */
  html {
    -webkit-text-size-adjust: 100%;
  }

  small {
    font-size: 80%;
  }

  sub, sup {
    position: relative;
    line-height: 0;
    font-size: x-small;
    vertical-align: baseline;
  }

  sub {
    bottom: -0.25em;
  }

  sup {
    top: -0.5em;
  }

  button, input, optgroup, select, textarea {
    font-family: inherit;
  }

  button, input {
    overflow: visible;
  }

  button, select {
    text-transform: none;
  }

  button, [type="button"], [type="reset"], [type="submit"] {
    -webkit-appearance: button
  }

  button::-moz-focus-inner, [type="button"]::-moz-focus-inner,
  [type="reset"]::-moz-focus-inner, [type="submit"]::-moz-focus-inner {
    border-style: none;
    padding: 0;
  }

  button:-moz-focusring, [type="button"]:-moz-focusring,
  [type="reset"]:-moz-focusring, [type="submit"]:-moz-focusring {
    outline: 1px dotted ButtonText;
  }

  textarea {
    resize: none;
    overflow: auto;
  }

  [type="checkbox"], [type="radio"] {
    box-sizing: border-box;
    padding: 0;
  }

  [type="number"]::-webkit-inner-spin-button, [type="number"]::-webkit-outer-spin-button {
    height: auto;
  }

  [type="search"] {
    -webkit-appearance: textfield;
    outline-offset: -2px;
  }

  [type="search"]::-webkit-search-decoration {
    -webkit-appearance: none;
  }

  ::-webkit-file-upload-button {
    -webkit-appearance: button;
    font: inherit;
  }

  abbr[title] {
    border-bottom: none;
    text-decoration: underline dotted;
  }

  progress {
    vertical-align: baseline;
  }

  details {
    display: block;
  }

  summary {
    display: list-item;
  }

  [hidden], template, hr {
    display: none;
  }

  b, strong {
    font-weight: 700;
  }

  ::placeholder {
    font-size: var(--font-small-size);
    color: var(--gray-color-400);
  }

  /* RESET */
  html, body, div, span, applet, object, iframe, h1, h2, h3, h4, h5, h6, p, blockquote, pre, a, abbr, acronym,
  address, big, cite, code, del, dfn, em, img, ins, kbd, q, s, samp, small, strike, strong, sub, sup, tt, var,
  b, u, i, center, dl, dt, dd, ol, ul, li, fieldset, form, label, legend, table, caption, tbody, tfoot, thead,
  tr, th, td, article, aside, canvas, details, embed, figure, figcaption, footer, header, hgroup,
  menu, nav, output, ruby, section, summary, time, mark, audio, video {
    margin: 0;
    padding: 0;
    border: 0;
    font-size: inherit;
    color: inherit;
    box-sizing: border-box;
    font-style: normal;
    white-space: nowrap;
    text-overflow: ellipsis;
  }

  dl, ul, ol, menu, li {
    list-style: none;
  }

  fieldset, img {
    border: 0 none;
  }

  input, select, textarea, button {
    vertical-align: middle;
    font-family: inherit;
  }

  button {
    cursor: pointer
  }

  address, caption, cite, code, dfn, em, var {
    font-style: normal;
    font-weight: normal;
  }

  input, textarea, img {
    max-width: 100%;
  }

  a, a:hover, a:visited, a:active {
    color: inherit;
    text-decoration: none;
  }

  svg, img {
    vertical-align: top;
  }

  button, select {
    -webkit-appearance: none;
    -moz-appearance: none;
    -ms-appearance: none;
    appearance: none;
  }

  select::-ms-expand {
    display: none;
  }

  /* IE 10, 11의 네이티브 화살표 숨기기 */
  table {
    width: 100%;
    table-layout: fixed;
    border-collapse: collapse;
    border-spacing: 0;
  }

  /* HTML5 for older browsers */
  article, aside, details, figcaption, figure, footer, header, hgroup, menu, nav, section {
    display: block;
  }

  html, body {
    width: 100%;
    height: 100%;
    min-height: 100%;
    font-size: var(--font-normal-size);
    line-height: 1.4;
    letter-spacing: 0.025em;
    color: var(--font-color);
    background-color: var(--bg-color);
  }
  .container {
      width: 430px;
      min-height: 100vh;
      height: 100dvh;
      margin: 0 auto;
      background-color: var(--bg-color);
  }
`

export default GlobalStyle
