// reducer.js

//초기 state값
export const initialState = {
    userId: '',
    password: '',
    userName: '',
    phone: '',
    birth: '',
    email: '',
    emailVerify: '',
    userIdError: '',
    passwordError: '',
    userNameError: '',
    phoneError: '',
    birthError: '',
};

//reducer 함수값
export function reducer(state, action) {
    switch (action.type) {
        case 'SET_FIELD':
            return {
                ...state,
                [action.field]: action.value,
            };
        case 'SET_ERROR':
            return {
                ...state,
                [`${action.field}Error`]: validationField(action.field, action.value),
            };
        default:
            return state;
    }
}

//각 입력 값에 대한 validation 체크부분
export function validationField(field, value) {
    switch (field) {
        case 'userId':
            if (value.length === 0 || !/^(?=.*[a-zA-Z])(?=.*\d)[a-zA-Z0-9]+$/.test(value)) return 'ID는 영문+숫자 조합으로 입력해주세요.';
            return value.length > 15 ? '아이디는 15자 이내입니다.' : '';
        case 'password':
            if (value.length === 0) return '비밀번호를 입력해주세요';
            return !/^[a-zA-Z0-9]+$/.test(value) ? '비밀번호는 영문,숫자만 입력해주세요.' : '';
        case 'userName':
            return value.length === 0 ? '이름을 입력해주세요.' : '';
        case 'phone':
            if (value.length === 0) return '전화번호를 입력해주세요.';
            return !/^\d{11}$/.test(value) ? '전화번호는 11자리 숫자로 입력해주세요.' : '';
        case 'birth':
            if (value.length === 0) return '생년월일 6자리를 입력해주세요.';
            return !/^\d{6}$/.test(value) ? '생년월일은 6자리 숫자로 입력해주세요.' : '';
    }
}
