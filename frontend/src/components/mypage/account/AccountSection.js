import React from "react";

const AccountSection = (props) => {
  console.log(props.account);
  return (
    <div className="bg-background-fill rounded-[20px] p-5">
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
      ) : null}
    </div>
  );
};

export default AccountSection;
