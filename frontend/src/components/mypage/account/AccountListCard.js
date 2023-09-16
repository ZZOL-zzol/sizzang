import AccountCard from "./AccountCard";
import { useEffect, useState } from "react";
import axios from "axios";
import { API_URL } from "../../../lib/constants";
import TextInput from "../../common/TextInput";
import OneCoinTransferModal from "./OneCoinTransferModal";

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
          userId: user.userId,
          accountName: newAccountName,
          accountNumber: newAccountNumber,
        }),
        {
          headers: { "Content-Type": "application/json" },
        }
      )
      .then((res) => {
        console.log(res);
        setOneCoinTransfer(false);
        document.getElementById("my_modal_1").close();
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
            window.location.replace("/profile");
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

      <OneCoinTransferModal
        oneCoinTransfer={oneCoinTransfer}
        onAccountNameChange={onAccountNameChange}
        newAccountName={newAccountName}
        newAccountNumber={newAccountNumber}
        onAccountNumberChange={onAccountNumberChange}
        onOneCoinTextChange={onOneCoinTextChange}
        oneCoinText={oneCoinText}
        onAuthenticateButtonClick = {()=>onAuthenticateButtonClick()}
        onOneCoinButtonClick={()=>onOneCoinButtonClick()}
      />
 
    </div>
  );
};

export default AccountListCard;
