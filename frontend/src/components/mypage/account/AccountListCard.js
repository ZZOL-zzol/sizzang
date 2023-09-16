import AccountCard from "./AccountCard";
import SearchBar from "../../common/SearchBar";
import { useEffect, useState } from "react";
import axios from "axios";
import { API_URL } from "../../../lib/constants";
import TextInput from "../../common/TextInput";

const AccountListCard = (props) => {
  const [everyAccountList, setEveryAccountList] = useState([]);
  const [newAccountName, setNewAccountName] = useState("");
  const [newAccountNumber, setNewAccountNumber] = useState("");
  const [oneCoinText, setOneCoinText] = useState("");
  const [oneCoinTransfer, setOneCoinTransfer] = useState(false);
  const user = JSON.parse(window.localStorage.getItem("User"));

  const onAccountNameChange = (e) => {
    setNewAccountName(e.target.value);
  };

  const onAccountNumberChange = (e) => {
    setNewAccountNumber(e.target.value);
  };

  const onOneCoinTextChange = (e) => {
    setOneCoinText(e.target.value);
  };

  useEffect(() => {
    axios
      .post(
        `${API_URL}/bank/v1/search/allAccounts`,
        JSON.stringify({ userId: user.userId }),
        { headers: { "Content-Type": "application/json" } }
      )
      .then((res) => {
        console.log(res.data);
        setEveryAccountList(res.data);
      })
      .catch((err) => console.log(err));
  }, []);

  const onAuthenticateButtonClick = () => {
    axios
      .post(
        `${API_URL}/bank/v1/auth/addAccountList`,
        JSON.stringify({
          userId: user.userID,
          accountName: newAccountName,
          accountNumber: newAccountNumber,
        }),
        {
          headers: { "Content-Type": "application/json" },
        }
      )
      .then((res) => {
        console.log(res);
      })
      .catch((err) => console.log(err));
  };

  const onOneCoinButtonClick = () => {
    console.log(newAccountNumber);
    axios
      .post(
        `${API_URL}/bank/v1/auth/1transfer`,
        JSON.stringify({
          bankCode: "119",
          accountNumber: newAccountNumber,
        }),
        {
          headers: { "Content-Type": "application/json" },
        }
      )
      .then((res) => {
        setOneCoinTransfer(true);
        console.log(res.data);
      })
      .catch((err) => console.log(err));
  };

  return (
    <div className="flex flex-col gap-3">
      <div className="flex w-full justify-between pr-5">
        <button
          className="btn btn-ghost normal-case text-xl font-environmentR"
          onClick={() => {
            props.setOpenAddAccount(false);
            window.location.replace("/profile")
          }}
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            height="1em"
            viewBox="0 0 384 512"
          >
            <path d="M342.6 150.6c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L192 210.7 86.6 105.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3L146.7 256 41.4 361.4c-12.5 12.5-12.5 32.8 0 45.3s32.8 12.5 45.3 0L192 301.3 297.4 406.6c12.5 12.5 32.8 12.5 45.3 0s12.5-32.8 0-45.3L237.3 256 342.6 150.6z" />
          </svg>
        </button>
        <div className="flex items-center text-xl font-bold">
          계좌 간편 등록
        </div>
      </div>

      <div className="text-lg font-bold self-start pl-2">
        등록할 계좌를 선택하세요.
      </div>

      {everyAccountList.map((account) => (
        <AccountCard
          account={account}
          type="addAccount"
          key={account.accountCode}
          accountList={props.accountList}
          setAccountList={props.setAccountList}
        />
      ))}
      <div
        className="flex justify-center items-center gap-2 text-outline"
        onClick={() => document.getElementById("my_modal_1").showModal()}
      >
        <svg
          className="fill-outline"
          xmlns="http://www.w3.org/2000/svg"
          height="1em"
          viewBox="0 0 512 512"
        >
          <path d="M256 512A256 256 0 1 0 256 0a256 256 0 1 0 0 512zM232 344V280H168c-13.3 0-24-10.7-24-24s10.7-24 24-24h64V168c0-13.3 10.7-24 24-24s24 10.7 24 24v64h64c13.3 0 24 10.7 24 24s-10.7 24-24 24H280v64c0 13.3-10.7 24-24 24s-24-10.7-24-24z" />
        </svg>
        새 계좌 등록
      </div>

      <dialog id="my_modal_1" className="modal">
        <div className="modal-box relative flex flex-col justify-between">
          <div className="modal-action">
            <form method="dialog">
              <button className="absolute btn bg-transparent flex top-5 right-5 border-none">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  height="1.5em"
                  viewBox="0 0 384 512"
                >
                  <path d="M342.6 150.6c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L192 210.7 86.6 105.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3L146.7 256 41.4 361.4c-12.5 12.5-12.5 32.8 0 45.3s32.8 12.5 45.3 0L192 301.3 297.4 406.6c12.5 12.5 32.8 12.5 45.3 0s12.5-32.8 0-45.3L237.3 256 342.6 150.6z" />
                </svg>
              </button>
            </form>
          </div>

          <h3 className="font-bold text-lg">새 계좌 등록</h3>
          <div className="flex flex-col gap-2">
            {oneCoinTransfer ? (
              <div className="flex gap-2 items-center text-start my-3">
                신한은행 입금내역을 확인하고 입금명 'ZZOL' 앞 네자리 숫자를
                확인해주세요!
              </div>
            ) : (
              <div className="flex gap-2 items-center">
                <img
                  src="./shc_symbol_ci.png"
                  alt="shinhan_logo"
                  className="w-8"
                />
                신한은행
              </div>
            )}

            <TextInput
              placeholder="계좌이름"
              onChangeEvent={onAccountNameChange}
              value={newAccountName}
            />
            <TextInput
              placeholder="계좌번호"
              onChangeEvent={onAccountNumberChange}
              value={newAccountNumber}
            />
            {oneCoinTransfer ? (
              <TextInput
                placeholder="네자리 숫자"
                onChangeEvent={onOneCoinTextChange}
                value={oneCoinText}
              />
            ) : null}
          </div>
          {oneCoinTransfer ? (
            <div
              className="w-full rounded-lg h-[40px] bg-myprimary mt-5 flex items-center justify-center text-white text-lg font-medium"
              onClick={() => onAuthenticateButtonClick()}
            >
              인증하기
            </div>
          ) : (
            <div
              className="w-full rounded-lg h-[40px] bg-myprimary mt-5 flex items-center justify-center text-white text-lg font-medium"
              onClick={onOneCoinButtonClick}
            >
              1원 보내기
            </div>
          )}
        </div>
      </dialog>
    </div>
  );
};

export default AccountListCard;
