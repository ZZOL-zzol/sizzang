import React from "react";

const AccountSection = (props) => {
  console.log(props.account);
  return (
    <div className="bg-background-fill rounded-lg p-5">
      {props.account ? (
        <div>
          <div className="flex justify-start mb-4">
            <img
              src="./shc_symbol_ci.png"
              alt="shinhan_logo"
              className="w-[50px] h-[50px] rounded-full overflow-hidden mr-2"
            />
            <div>
              <p className="text-lg font-bold">{props.account.accountName}</p>
              <p className="text-sm">{props.account.accountNumber}</p>
            </div>
          </div>
          <p className="text-2xl font-bold text-center mb-4">
            {Number(props.account.accountBalance).toLocaleString()}원
          </p>
          <div
            className="text-sm text-center"
            onClick={() => props.setOpenAddAccount(true)}
          >
            대표 계좌 변경하기 &gt;
          </div>
        </div>
      ) : (
        <div>
          <p className="text-lg">아직 대표 계좌가 없습니다.</p>
          
          <div
            className="text-lg text-center font-bold flex justify-center items-center gap-2"
            onClick={() => props.setOpenAddAccount(true)}
          >
            <svg
          
          xmlns="http://www.w3.org/2000/svg"
          height="1em"
          viewBox="0 0 512 512"
        >
          <path d="M256 512A256 256 0 1 0 256 0a256 256 0 1 0 0 512zM232 344V280H168c-13.3 0-24-10.7-24-24s10.7-24 24-24h64V168c0-13.3 10.7-24 24-24s24 10.7 24 24v64h64c13.3 0 24 10.7 24 24s-10.7 24-24 24H280v64c0 13.3-10.7 24-24 24s-24-10.7-24-24z" />
        </svg>
            대표 계좌 등록하기 &gt;
          </div>
        </div>
      )}
    </div>
  );
};

export default AccountSection;
