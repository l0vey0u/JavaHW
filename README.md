### N진수 계산기 만들기

---

1. 프로그램 내 기능
   - N 진수로 Formatting ( Ex . N  = 13, num = 169 to 1_0_0_(13)[169] )
   - 변수 설정 및 값 할당 기능
     - 변수 이름 : String
     - 변수 값 : Integer
   - 변수 간 값 대입 및 계산 기능 ( var3 = var1 + var2 )
      - 표시 + 라고만 있지만 원하는 수식을 쓰는 곳
      - 계산 식 입력이 더 어울릴 듯
      - 덧셈 / 뻴셈 과정을 차근히 보여주고 값 올림 , 내림시 carry , borrow 출력
      - 뺄셈시 - 결과가 나오려 할 땐 계산하지 않고 좌변 값을 출력
         -  이 기능을 위해 비교 메소드가 요구 됨 ( override compareTo )
   - 그 외 입력시 종료
   - 계산 결과 or 변수내 설정된 데이터 값을 N진수 포맷팅해서 매 기능시 출력
2.  Class 구조
   - BaseNumber
     - 정수에 대한 진수 처리를 하는 객체
     - 생성자로 정수와 진수 값을 받는 것이 좋을 듯
     - 내부 ArrayList에 진수들이 저장되어 있다.
     - 그냥 개인적인 생각인데 적당히 몇개가 생길지는 계산가능하니 Arraylist를 안써도 될듯?
     - 로그 계산과 Arraylist 내부 연산중 어떤게 더 빠를려나 ..
     - toString
       - Format Msg : ArrayList(base)[n] ( Ex. 1_0_0_(13)[169] ) 
     - compareTo
       - BaseNumber instance를 전달받으면 비교 처리 후 
          같으면 0 , 좌변이 크면 양수, 우변이 크면 음수 리턴
     - plus 
       - BaseNumber instance를 전달 받고 더하는데 과정 마다 단계를 출력
       - carry 여부 출력 ( carry 란 다음 자리수에 1 더해야 되는 상황을 말함 )
         - 이를 위해선 boolean carry 필요할듯
       - 계산 결과를 리턴
     - minus
       - 위와 같고 특이 사항도 위와 같음
   - BaseCalc