import { useLocation } from "react-router-dom";
import Header from "../components/common/Header";
import Navbar from "../components/common/Navbar";
import HistoryCard from "../components/history/HistoryCard";
import { useEffect, useState } from "react";
import axios from "axios";
import { API_URL } from "../lib/constants";


// const historyList = [
//   {puCode : 1, stCode : 1, puCost : 10000, puDate : '2023-09-13', userAccount:"110184999999", ppList:[{ppName:'양념치킨', ppInt:'2'},{ppName:'양념치킨', ppInt:'2'},{ppName:'양념치킨', ppInt:'2'}]},
//   {puCode : 1, stCode : 1, puCost : 10000, puDate : '2023-09-13', userAccount:"110184999999", ppList:[{ppName:'양념치킨', ppInt:'2'},{ppName:'양념치킨', ppInt:'2'},{ppName:'양념치킨', ppInt:'2'}]},
//   {puCode : 1, stCode : 1, puCost : 10000, puDate : '2023-09-13', userAccount:"110184999999", ppList:[{ppName:'양념치킨', ppInt:'2'},{ppName:'양념치킨', ppInt:'2'},{ppName:'양념치킨', ppInt:'2'}]},
//   {puCode : 1, stCode : 1, puCost : 10000, puDate : '2023-09-13', userAccount:"110184999999", ppList:[{ppName:'양념치킨', ppInt:'2'},{ppName:'양념치킨', ppInt:'2'},{ppName:'양념치킨', ppInt:'2'}]},
// ]

const HistoryPage = () => {
 
  const location = useLocation();
  console.log(location.state.accountNumber)
  const [accountNumber, setAccountNumber] = useState(
    location.state.accountNumber
  );
    const [historyList, setHistoryList] = useState([])

  useEffect(() => {
    axios.post(
      `${API_URL}/purchase/get`, JSON.stringify({accountNumber: accountNumber}), {headers : { "Content-Type" : "application/json"}}
    ).then(res => setHistoryList(res.data.data))
    .catch(err => console.log(err))
  }, []);

  return (
    <div className="w-full bg-background-fill">
      <Header title="내 소비내역" backButton route="/profile" />
      <div className="flex flex-col w-full h-full py-20 gap-2">
        {historyList.map((history) => (
          <HistoryCard history={history} accountNumber={accountNumber}/>
        ))}
      </div>
      <Navbar />
    </div>
  );
};

export default HistoryPage;
