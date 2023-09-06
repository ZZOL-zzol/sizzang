import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import TextButton from '../components/common/TextButton';
import TextInput from '../components/common/TextInput';

// const KAKAO_START_URL = 'http://i9a810.p.ssafy.io:8080/oauth/login/kakao';

const EntrancePage = () => {

  return (
    <div className=" w-full h-full bg-white outline outline-1">
      <div className="flex w-full h-1/2 items-center justify-center">
        <div>로고</div>
      </div>
      <div className="flex flex-col h-1/2 justify-evenly  items-center">
        <div className='flex flex-col gap-2'>
        <TextInput placeholder='아이디를 입력하세요'/>
        <TextInput placeholder='비밀번호를 입력하세요'/>
        </div>
        <Link to={'/main'} className="w-3/4">
          <TextButton
            icon="kakao"
            type="kakao"
            size="medium"
          />
        </Link>
      </div>
    </div>
  );
};

export default EntrancePage;
