# 📄 API 상세 문서 (PlanIt)
PlanIt 전체 API 대상으로 Postman 호출 테스트 결과 입니다.

## 회원가입
**URL** : POST /register

성공
![image](https://github.com/user-attachments/assets/e63ab61a-1cbf-4722-89de-a2a100c4ab86)

실패
![image](https://github.com/user-attachments/assets/bb59e9ce-a54e-4cf6-994c-383645965001)

## 로그인
**URL** : POST /login

성공
![image](https://github.com/user-attachments/assets/c47ee155-bdec-4dd5-b489-1703765c3732)

실패
![image](https://github.com/user-attachments/assets/bc47d0b6-da2a-4240-b9bc-7f62cbc759e9)

## 이메일 인증 요청
**URL** : POST /mailSend

성공
![image](https://github.com/user-attachments/assets/57c46263-3fa7-4a43-a367-f5d27461b9f3)
![image](https://github.com/user-attachments/assets/ec979329-924a-4ecf-80b8-c86a2a45bdbb)

실패
![image](https://github.com/user-attachments/assets/65f3e59c-815c-433b-85a2-f9c7619dc936)

## 이메일 인증번호 검증
**URL** : POST /mailNumberCheck

성공
![image](https://github.com/user-attachments/assets/523af458-fa91-4575-a3c4-76253fe87074)

실패
![image](https://github.com/user-attachments/assets/f9efdf3e-cfba-431c-877a-86d6efb03d36)

## ID 찾기
**URL** : 

성공
![image](https://github.com/user-attachments/assets/591cad97-f948-4818-8634-d9cbeb1019a7)

실패
![image](https://github.com/user-attachments/assets/ff9a954d-ac7f-443d-8ac0-978440332aea)

## PW 찾기
**URL** : POST /userPwSearch

성공
![image](https://github.com/user-attachments/assets/be3dc854-b1a1-4d06-8080-4c8f9d2ee521)

실패
![image](https://github.com/user-attachments/assets/0f0510e0-a4d5-4fb5-9ab1-5307ccd95d98)

## ID 변경
**URL** : POST /setNewPw

성공
![image](https://github.com/user-attachments/assets/a1e047ab-35b6-4ba3-af9f-a8b835c478cd)

실패
![image](https://github.com/user-attachments/assets/0f2316e2-3bc6-4c9f-b32d-31cd5420eb2e)

## Role 변경
**URL** : POST /roleChange

성공
![image](https://github.com/user-attachments/assets/851b9afa-3d45-4f42-bbbf-9024da5204ff)

실패
![image](https://github.com/user-attachments/assets/d42f7e97-6aa3-4a20-80fb-209446b5d683)

## 내 정보 진입 시 비밀번호 확인
**URL** : POST /myProfile

성공
![image](https://github.com/user-attachments/assets/d668ddca-75bb-4393-881b-ccd2e3ad63b7)

실패
![image](https://github.com/user-attachments/assets/295f5a93-3303-48e4-a505-508e9ff91037)

## 내 정보에서 개인정보 수정
**URL** : POST /updateUser

성공
![image](https://github.com/user-attachments/assets/9dac9c26-a271-40f3-b003-504b3f320d4b)

실패
![image](https://github.com/user-attachments/assets/21ba2703-4d78-4122-9c65-e7a3469d6991)

## 내 모든 일정 조회
**URL** : GET /main
**header** : Authorization: Bearer {accessToken}

성공
![image](https://github.com/user-attachments/assets/aab5d83b-4343-4193-b063-f00daf8f9782)

## 신규 일정 등록
**URL** : POST /createPlan
**header** : Authorization: Bearer {accessToken}

성공
![image](https://github.com/user-attachments/assets/8371bba7-da9a-4bb9-a183-9b0082db0d16)

## 일정 수정
**URL** : POST /updatePlan/{planID}
**header** : Authorization: Bearer {accessToken}

성공
![image](https://github.com/user-attachments/assets/fb3c9323-ee04-4537-9932-a546fba9b702)

실패
![image](https://github.com/user-attachments/assets/c202890a-5ed1-4105-8bdc-7d50f70e0f69)


## 일정 삭제
**URL** : POST /deletePlan/{planId}
**header** : Authorization: Bearer {accessToken}

성공
![image](https://github.com/user-attachments/assets/c3b7faed-afe6-4c96-a44f-637495269fe5)

실패
![image](https://github.com/user-attachments/assets/5765c6ac-9eac-4619-8c48-9986abaea40a)


## 특정 일자 일정 조회
**URL** : GET /main/date
**header** : Authorization: Bearer {accessToken}
**param** : date

성공
![image](https://github.com/user-attachments/assets/a643e705-59c2-434f-a1d8-e5148fa4f659)

## 특정 월 일정 조회
**URL** : GET /main/month
**header** : Authorization: Bearer {accessToken}
**param** : month
**param** : year

성공
![image](https://github.com/user-attachments/assets/ade7eee0-79d9-4954-89ba-011fae224672)

## 알람
**URL** : GET /send
웹소켓 사용으로 인해 Postman을 통한 직접적인 테스트가 어려워, 콘솔 로그 출력을 통해 테스트 진행 및 정상 동작 여부 확인하였습니다.

성공
![image](https://github.com/user-attachments/assets/3b87ed64-b267-4ff8-9a69-0db8280ee3fc)

