import Header from '../components/common/Header';
import Navbar from '../components/common/Navbar';

const MainPage = () => {
  return (
    <div className="App flex flex-col text-3xl h-full w-full">
      <Header/>
      <div className='h-96  flex flex-col flex-grow'>바디이이이이</div>
      <Navbar/>
    </div>
  );
}

export default MainPage;
