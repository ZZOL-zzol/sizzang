import { useEffect, useState } from "react";
import HistoryCard from "../../history/HistoryCard";
import { API_URL } from "../../../lib/constants";
import axios from "axios";

const HistoryEditCard = (props) => {
  const [historyList, setHistoryList] = useState([]);

  useEffect(() => {
    console.log(props.account);
    // axios
    //   .post(
    //     `${API_URL}/bank/v1/search/transaction`,
    //     JSON.stringify({
    //       dataHeader: {
    //         apikey: "2023_Shinhan_SSAFY_Hackathon",
    //       },
    //       dataBody: {
    //         계좌번호: props.account.accountNumber,
    //       },
    //     }),
    //     { headers: { "Content-Type": "application/json" } }
    //   )
    //   .then((res) => console.log(res))
    //   .catch((err) => console.log(err));
  }, []);

  return (
    <div className="flex flex-col items-center gap-3">
      <div className="flex w-full justify-between">
        <button
          className="btn btn-ghost normal-case text-xl font-environmentR"
          onClick={() => props.setOpenHistoryEdit(false)}
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
          거래 내역 관리
        </div>
        {historyList.map((history) => (
          <HistoryCard history={history} />
        ))}
      </div>
    </div>
  );
};

export default HistoryEditCard;
