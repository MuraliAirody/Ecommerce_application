import React from 'react';
import AliceCarousel from 'react-alice-carousel';
import 'react-alice-carousel/lib/alice-carousel.css';
import { MainCarouselData } from './MainCarouselData';

const items = MainCarouselData.map((item)=><img className='cursor-pointer' role='presentation'
src={item.image}/>)

const MainCarousel = () => (
    <AliceCarousel
        autoPlay
        autoPlayInterval={1800}
        infinite
        disableButtonsControls
        items={items}
    />
);

export default MainCarousel