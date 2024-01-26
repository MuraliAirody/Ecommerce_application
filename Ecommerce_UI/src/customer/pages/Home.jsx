import React, { useState } from "react";
import MainCarousel from "../components/Home_Carousel/MainCarousel";
import HomeSectionsCarousel from "../components/Home_Sections_Carousel/HomeSectionsCarousel";
import { mens_kurta } from "../../Data/Men/men_kurta";
import { mensShoesPage1 } from "../../Data/shoes";
import { sareePage1 } from "../../Data/Saree/page1";
import { dressPage1 } from "../../Data/dress/page1";
import { gounsPage1 } from "../../Data/Gouns/gouns";
import { lengha_page1 } from "../../Data/Women/LenghaCholi";

function Home() {

  return (
    <div>
      <div>
        <MainCarousel></MainCarousel>
      </div>
      <div className="space-y-10 py-20 flex flex-col justify-center px-5 lg:px-10">
        <HomeSectionsCarousel data={mens_kurta} sectionName={"Men's Kurta"}/>
        <HomeSectionsCarousel data={mensShoesPage1} sectionName={"Men's Shoe"}/>
        <HomeSectionsCarousel data={sareePage1} sectionName={"Women's Saree"}/>
        <HomeSectionsCarousel data={gounsPage1} sectionName={"Women's Gouns"}/>
        <HomeSectionsCarousel data={lengha_page1} sectionName={"Women's Lengha"}/>
      </div>
    </div>
  );
}

export default Home;
