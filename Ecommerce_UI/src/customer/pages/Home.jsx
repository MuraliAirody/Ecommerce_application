import React, { useState } from "react";
import MainCarousel from "../components/Home_Carousel/MainCarousel";
import HomeSectionsCarousel from "../components/Home_Sections_Carousel/HomeSectionsCarousel";
import { mens_kurta } from "../../Data/Mens/mens_kurta";

function Home() {

  return (
    <div>
      <div>
        <MainCarousel></MainCarousel>
      </div>
      <div className="space-y-10 py-20 flex flex-col justify-center px-5 lg:px-10">
        <HomeSectionsCarousel data={mens_kurta} sectionName={"Men's Kurta"}/>
        <HomeSectionsCarousel data={mens_kurta} sectionName={"Men's Shoe"}/>
        <HomeSectionsCarousel data={mens_kurta} sectionName={"Men's Shirts"}/>
        <HomeSectionsCarousel data={mens_kurta} sectionName={"Women's Dress"}/>
        <HomeSectionsCarousel data={mens_kurta} sectionName={"Women's Saree"}/>
      </div>
    </div>
  );
}

export default Home;
